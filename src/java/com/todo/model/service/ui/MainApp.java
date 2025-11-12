package com.todo.ui;

import com.todo.service.TodoList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    
    // Instanciation de l'objet service pour gérer les tâches
    private static TodoList todoList = new TodoList();
    
    // Outil pour lire les entrées utilisateur
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🚀 Bienvenue dans l'application Todo List en Java!");
        
        // Boucle principale de l'application
        int choice;
        do {
            displayMenu();
            choice = readUserChoice();

            switch (choice) {
                case 1:
                    todoList.displayTasks();
                    break;
                case 2:
                    handleAdd();
                    break;
                case 3:
                    handleComplete();
                    break;
                case 4:
                    handleDelete();
                    break;
                case 5:
                    System.out.println("👋 Merci d'avoir utilisé l'application. Au revoir!");
                    break;
                default:
                    System.out.println("⚠️ Choix invalide. Veuillez sélectionner une option entre 1 et 5.");
            }
        } while (choice != 5);
        
        // Fermeture du scanner à la sortie
        scanner.close();
    }

    // Affiche le menu à l'utilisateur
    private static void displayMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Afficher toutes les tâches");
        System.out.println("2. Ajouter une nouvelle tâche");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Supprimer une tâche");
        System.out.println("5. Quitter l'application");
        System.out.print("Entrez votre choix (1-5) : ");
    }
    
    // Lit le choix de l'utilisateur en gérant les erreurs d'entrée non numérique
    private static int readUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            // Gère les lettres ou chaînes de caractères au lieu de nombres
            scanner.next(); // Vide le buffer de l'entrée invalide
            return -1; // Retourne une valeur invalide pour que le switch gère l'erreur
        } finally {
            // Consomme la fin de ligne restante après nextInt pour les entrées suivantes
            scanner.nextLine(); 
        }
    }

    // Logique pour l'option 2: Ajouter une tâche
    private static void handleAdd() {
        System.out.print("Entrez la description de la nouvelle tâche : ");
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()) {
            todoList.addTask(description);
        } else {
            System.out.println("La description ne peut pas être vide.");
        }
    }

    // Logique pour l'option 3: Marquer comme terminée
    private static void handleComplete() {
        // Affiche la liste pour aider l'utilisateur à choisir l'index
        todoList.displayTasks(); 
        
        System.out.print("Entrez le NUMÉRO de la tâche à compléter : ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            todoList.completeTask(index);
        } else {
            System.out.println("⚠️ Entrée invalide. Veuillez entrer un numéro.");
        }
    }

    // Logique pour l'option 4: Supprimer une tâche
    private static void handleDelete() {
        // Affiche la liste pour aider l'utilisateur à choisir l'index
        todoList.displayTasks(); 
        
        System.out.print("Entrez le NUMÉRO de la tâche à supprimer : ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            todoList.deleteTask(index);
        } else {
            System.out.println("⚠️ Entrée invalide. Veuillez entrer un numéro.");
        }
    }
}