package com.server.restservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.server.restservice.models.MinimalUser;
import com.server.restservice.models.User;
import com.server.restservice.operation.JsonOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserFollowService {
    public static String COLLECTION_NAME = "users";
    public static Object followUser(String loggedUid, String followUid) throws ExecutionException, InterruptedException {
        List<String> followedUsers = new ArrayList<>();


        User user = UserService.getUserDetails(loggedUid);

        if(user.getFollowed_users() != null) {
            followedUsers = user.getFollowed_users();
        }

        if(followedUsers.contains(followUid)) {
            return JsonOperation.createJson("Error","Already following user");
        }

        followedUsers.add(followUid);

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(followUid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(!document.exists()) {
            return JsonOperation.createJson("Error","User with specified uid does not exist!");
        }


        dbFirestore.collection(COLLECTION_NAME).document(loggedUid).update(Collections.singletonMap("followed_users", followedUsers));

        return JsonOperation.createJson("Success","User followed successfully");
    }

    public static Object unfollowUser(String loggedUid, String followUid) throws ExecutionException, InterruptedException {
        List<String> followedUsers = new ArrayList<>();


        User user = UserService.getUserDetails(loggedUid);

        if(user.getFollowed_users() != null) {
            followedUsers = user.getFollowed_users();
        }

        if(!followedUsers.contains(followUid)) {
            return JsonOperation.createJson("Error","The specified user is not being followed");
        }
        followedUsers.remove(followUid);

        Firestore dbFirestore = FirestoreClient.getFirestore();

        dbFirestore.collection(COLLECTION_NAME).document(loggedUid).update(Collections.singletonMap("followed_users", followedUsers));

        return JsonOperation.createJson("Success","User unfollowed successfully");
    }
    public static Object getFollowers(String uid) throws ExecutionException, InterruptedException {
        List<MinimalUser> followedUsers = new ArrayList<>();
        User user = UserService.getUserDetails(uid);
        MinimalUser followedUser;
        for(String followedUserUid : user.getFollowed_users()) {
            followedUser = UserService.getUserDetailsMinimal(followedUserUid);
            if(followedUser != null) {
                followedUsers.add(followedUser);
            }
        }
        return followedUsers;
    }

}
