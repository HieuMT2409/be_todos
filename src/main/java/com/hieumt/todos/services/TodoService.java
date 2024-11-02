package com.hieumt.todos.services;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hieumt.todos.models.TodoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TodoService {

    private static final String COLLECTION_NAME = "todos";

    // post - method
    public String saveTodo(TodoItem todoItem) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document();
        todoItem.setId(documentReference.getId());
        ApiFuture<WriteResult> writeResult = documentReference.set(todoItem);
        writeResult.get();

        return documentReference.getId();
    }

    // get - method
    public List<TodoItem> getAllTodos() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<TodoItem> todos = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            TodoItem todoItem = document.toObject(TodoItem.class);
            todoItem.setId(document.getId());
            todos.add(todoItem);
        }
        return todos;
    }

    public TodoItem getTodoById(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            TodoItem todoItem = document.toObject(TodoItem.class);
            if (todoItem != null) {
                todoItem.setId(document.getId());
            }
            return todoItem;
        } else {
            return null;
        }
    }

    // update - method
    public String updateTodo(String id, TodoItem todoItem) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(id).set(todoItem);
        return writeResult.get().getUpdateTime().toString();
    }

    // delete - method
    public String deleteTodo(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }


}
