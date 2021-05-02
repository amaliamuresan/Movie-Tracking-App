package com.server.restservice.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.server.restservice.data.ServerData;
import com.server.restservice.models.MinimalUser;
import com.server.restservice.operation.JsonOperation;
import com.server.restservice.models.User;
import io.grpc.Server;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private static final String COLLECTION_NAME = "users";

    public boolean checkLegitUser(User user, String token) {
        System.out.println(token);
        System.out.println("This is the uid " + user.getUid());
        if(token == null)
            return false;
        if(user.getUid() == null)
            return false;
        else {
            String serverToken = ServerData.getToken(user.getUid());
            return serverToken != null && serverToken.equals(token);
        }
    }

    public String checkLegitUser(String uid, String token) {
        System.out.println(token);
        System.out.println("This is the uid " + uid);
        if(token == null)
            return "Missing token";
        if(uid == null)
            return "Missing uid";
        else {
            String serverToken = ServerData.getToken(uid);
            if(ServerData.isBlocked(uid)) {
                return "Account is temporarily blocked";
            }
            if(serverToken != null && serverToken.equals(token)) {
                    return  "Success";
            } else {
                ServerData.addStrike(uid);
                return "Invalid Token";
            }
        }
    }

    public Object saveUser(User user) {
        // Firebase Auth variables
        UserRecord userRecord = null;
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(true)
                .setPassword(user.getPassword());
        //.setDisplayName(user.getUsername())
        //.setDisabled(false);
        try {
            userRecord = FirebaseAuth.getInstance().createUser(request);
        } catch (FirebaseAuthException e) {
            return JsonOperation.createJson("Error", e.getAuthErrorCode().toString());
        }
        if(userRecord != null)
        {
            String uid = userRecord.getUid();
            List<String> emptyList = new ArrayList<>();
            Firestore dbFirestore = FirestoreClient.getFirestore();
            user.setUid(uid);
            user.setFollowed_users(emptyList);
            user.setTo_watch_movies(emptyList);
            user.setWatched_movies(emptyList);

            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(uid).set(user);
            return JsonOperation.createJson("uid",uid);
        }
        //ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document().set(user);
        return JsonOperation.createJson("Error", "Error while creating user");
    }

    public static User getUserDetails(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(uid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists())
        {
            return document.toObject(User.class);
        }
        else
        {
            return null;
        }
    }

    public static MinimalUser getUserDetailsMinimal(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(uid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists())
        {
            return document.toObject(MinimalUser.class);
        }
        else
        {
            return null;
        }
    }

    public Object updateUser(String uid, Map<String,Object> data) throws ExecutionException, InterruptedException {


        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(uid).update(data);

        return JsonOperation.createJson("Success","Account updated successfully");
    }

    public Object loginUser(User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(COLLECTION_NAME).whereEqualTo("email", user.getEmail()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        User remoteUser = documents.get(0).toObject(User.class);
        System.out.println("USERNAME + " + remoteUser.getEmail());
        String token = null;
        if(ServerData.isBlocked(remoteUser.getUid())) {
            return JsonOperation.createJson("Error", "Account blocked temporarily.");
        }
        if(remoteUser.getEmail().equals(user.getEmail()) && remoteUser.getPassword().equals(user.getPassword()))
        {
            String uid = remoteUser.getUid();
            if(ServerData.getToken(uid) != null)
            {
                ServerData.removeToken(uid);
            }
            ServerData.resetStrikes(uid);

            token = FirebaseAuth.getInstance().createCustomToken(remoteUser.getUid());
            ServerData.addToken(remoteUser.getUid(),token);
            System.out.println(token);


            Hashtable<String, String> returnData = new Hashtable<>();

           returnData.put("token",token);
            returnData.put("uid", remoteUser.getUid());

            return JsonOperation.createJson(returnData);
            //remoteUser.setToken(token);
            //return remoteUser;
        }
        else{
            ServerData.addStrike(remoteUser.getUid());
            return JsonOperation.createJson("Error", "Invalid credentials");
        }

    }

    public Object searchUser(String query) throws ExecutionException, InterruptedException {
        List<MinimalUser> resultList = new ArrayList<>();
        MinimalUser minimalUser;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(COLLECTION_NAME).whereGreaterThanOrEqualTo("display_name", query).whereLessThanOrEqualTo("display_name", query + '\uf8ff').get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        User remoteUser = documents.get(0).toObject(User.class);
        for(QueryDocumentSnapshot snapshot : documents) {
            minimalUser = snapshot.toObject(MinimalUser.class);
            resultList.add(minimalUser);
        }
        return resultList;
    }
}
