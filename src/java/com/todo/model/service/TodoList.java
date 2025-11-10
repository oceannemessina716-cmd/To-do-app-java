package com.todo.service;

import com.todo.model.Task;
import java.util.ArrayList;

/**
 * Gère la logique métier de l'application : stockage et manipulation des tâches.
 */
public class TodoList {
    
    // Attribut : Le conteneur interne pour stocker les objets Task
    private ArrayList<Task> tasks;

    // Constructeur
    public TodoList() {
        // Initialisation de la liste au démarrage du service
        this.tasks = new ArrayList<>();
    }

    // 1. AJOUTER une tâche
    public void addTask(String description) {
        // Crée un nouvel objet Task et l'ajoute à la liste
        tasks.add(new Task(description));
        System.out.println("✅ Tâche ajoutée : " + description);
    }

    // 2. AFFICHER les tâches
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\n--- La liste de tâches est vide ! ---");
            return;
        }
        System.out.println("\n--- LISTE DE TÂCHES ---");
        for (int i = 0; i < tasks.size(); i++) {
            // Affichage avec l'index base 1 pour l'utilisateur
            System.out.println((i + 1) + ". " + tasks.get(i).toString()); 
        }
        System.out.println("------------------------");
    }

    // 3. MARQUER une tâche comme terminée (Utilise l'index base 1)
    public boolean completeTask(int index) {
        // Vérifie si l'index est valide (doit être entre 1 et la taille de la liste)
        if (index > 0 && index <= tasks.size()) {
            Task taskToComplete = tasks.get(index - 1); // Conversion de l'index base 1 en base 0
            taskToComplete.markAsCompleted();
            System.out.println("🎉 Tâche " + index + " marquée comme terminée !");
            return true;
        }
        System.out.println("❌ Erreur : Index de tâche non valide.");
        return false;
    }

    // 4. SUPPRIMER une tâche (Utilise l'index base 1)
    public boolean deleteTask(int index) {
        // Vérifie si l'index est valide
        if (index > 0 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1); // Conversion de l'index base 1 en base 0 et suppression
            System.out.println("🗑️ Tâche supprimée : " + removedTask.getDescription());
            return true;
        }
        System.out.println("❌ Erreur : Index de tâche non valide.");
        return false;
    }
}