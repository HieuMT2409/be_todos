package com.hieumt.todos.services;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hieumt.todos.models.ProjectItem;
import com.hieumt.todos.models.TodoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {

    private static final String COLLECTION_NAME = "projects";

    // post - method
    public String saveProject(ProjectItem projectItem) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document();
        projectItem.setId(documentReference.getId());
        ApiFuture<WriteResult> writeResult = documentReference.set(projectItem);
        writeResult.get();

        return documentReference.getId();
    }

    // get - method
    public List<ProjectItem> getAllProjects() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ProjectItem> items = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            ProjectItem projectItem = document.toObject(ProjectItem.class);
            projectItem.setId(document.getId());
            items.add(projectItem);
        }
        return items;
    }

    public ProjectItem getProjectById(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            ProjectItem projectItem = document.toObject(ProjectItem.class);
            if (projectItem != null) {
                projectItem.setId(document.getId());
            }
            return projectItem;
        } else {
            return null;
        }
    }

    // update - method
    public String updateProject(String id, ProjectItem projectItem) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(id).set(projectItem);
        return writeResult.get().getUpdateTime().toString();
    }

    // delete - method
    public String deleteProject(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }


}
