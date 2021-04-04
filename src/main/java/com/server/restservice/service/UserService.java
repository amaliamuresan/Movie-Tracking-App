package com.server.restservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.server.restservice.data.ServerData;
import com.server.restservice.operation.JsonOperation;
import com.server.restservice.models.User;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private static final String COLLECTION_NAME = "users";

    public Boolean checkUniqueUsername(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(COLLECTION_NAME).whereEqualTo("username", user.getUsername()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        System.out.println(user.getUsername());
        System.out.println(documents.size());
        return documents.isEmpty();

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
            Firestore dbFirestore = FirestoreClient.getFirestore();
            user.setUid(uid);
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(uid).set(user);
            return JsonOperation.createJson("uid",uid);
        }
        //ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document().set(user);
        return JsonOperation.createJson("Error", "Error while creating user");
    }

    public User getUserDetails(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(uid);
        //DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document().;
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

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        //ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getName()).set(user);
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getUid()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Object loginUser(User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(COLLECTION_NAME).whereEqualTo("email", user.getEmail()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        User remoteUser = documents.get(0).toObject(User.class);
        System.out.println("USERNAME + " + remoteUser.getEmail());
        String token = null;
        if(remoteUser.getEmail().equals(user.getEmail()) && remoteUser.getPassword().equals(user.getPassword()))
        {
            String uid = remoteUser.getUid();
            if(ServerData.getToken(uid) != null)
            {
                ServerData.removeToken(uid);
            }
            token = FirebaseAuth.getInstance().createCustomToken(remoteUser.getUid());
            ServerData.addToken(remoteUser.getUid(),token);
            System.out.println(token);

            Hashtable<String, String> returnData = new Hashtable<>();
            returnData.put("token",token);
            returnData.put("uid",remoteUser.getUid());

            return JsonOperation.createJson(returnData);
        }
        else{
            return JsonOperation.createJson("Error", "Invalid credentials");
        }

    }
}
