package com.server.restservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.server.restservice.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private static final String COLLECTION_NAME = "users";

    public Boolean checkUniqueUsername(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection("COLLECTION_NAME").whereEqualTo("username", user.getUsername()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        System.out.println(user.getUsername());
        System.out.println(documents.size());
        return documents.isEmpty();

    }

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        //if(checkUniqueUsername(user))
        if(true)
        {
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
                //System.out.println(e.getErrorCode());
                //if(e.getErrorCode().equals("ALREADY_EXISTS"))
                //{
                    return e.getAuthErrorCode().toString();
                //}
            }
            if(userRecord != null)
            {
                String uid = userRecord.getUid();
                Firestore dbFirestore = FirestoreClient.getFirestore();
                user.setUid(uid);
                ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(uid).set(user);
                return collectionApiFuture.get().getUpdateTime().toString();
            }
            //ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document().set(user);
            return "Error while creating user";
        }
        else
            return "Error: Username is not unique";
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
}
