package WithDB;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Dao dao = new Dao(PostgreSQL.getConnection());
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. traer todos los posteos");
            System.out.println("2. traer posteos por usuario");
            System.out.println("3. traer posteos de la base de datos");
            System.out.println("4. Exit");
            System.out.print("elige una opcion: ");
            option = sc.nextInt();
            ApiClient apiClient = new ApiClient();
            switch (option) {
                case 1:
                    List<Post> posts = tratarPosts(transformToPostList(apiClient.getPosts()));
                    System.out.println("Traer todos los posteos:");
                    posts.forEach(System.out::println);
                    manuDB(posts, dao);
                    break;
                case 2:
                    System.out.print("ingresa el ID del usuario: ");
                    int userId = sc.nextInt();
                    List<Post> userPosts = tratarPosts(transformToPostList(apiClient.getPostsByUserId(userId)));
                    userPosts.forEach(System.out::println);
                    manuDB(userPosts, dao);
                    break;
                case 3:
                    System.out.println("Traer posteos de la base de datos:");
                    List<Post> dbPosts = dao.findAll();
                    if (dbPosts.isEmpty()) {
                        System.out.println("No hay posteos en la base de datos.");
                    } else {
                        dbPosts.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("opcion no valida, por favor intenta de nuevo.");
            }
        } while (option != 4);
    }

    public static List<Post> transformToPostList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(json, Post[].class));
        } catch (Exception e) {
            throw new RuntimeException("Error transformando el Json: ", e);
        }
    }

    public static List<Post> tratarPosts(List<Post> posts) {
        posts.forEach(post -> post.setTitle(post.getTitle().toUpperCase()));
        posts.forEach(post -> post.setDate(new Timestamp(System.currentTimeMillis())));
        return posts;
    }

    public static void manuDB(List<Post> posts,Dao dao) {
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Insertar Posts");
            System.out.println("0. Salir del menu de DB");
            switch (option = sc.nextInt()) {
                case 1 -> {
                    posts.forEach(dao::insert);
                    System.out.println("Posts insertados correctamente.");
                }
                case 0 -> System.out.println("Saliendo del menu de DB...");
                default -> System.out.println("Opcion no valida, por favor intenta de nuevo.");
            }
        }while (option!=0);
    }
}
