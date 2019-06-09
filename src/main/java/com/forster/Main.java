package com.forster;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Main mainObj = new Main();

        System.out.println("loading input json...");
        File inputJsonFile = mainObj.loadInputFile();
        System.out.println("loaded input json...");

        System.out.println("Extracting movies from input json...");
        List<Movies> moviesList = mainObj.extractMoviesFromJson(inputJsonFile);
        System.out.println("Extracted movies from input json...; moviesCount : "+moviesList.size());

        System.out.println("Grouping movies by FSK Categories..");
        Map<String, ArrayList<Movie>> movieMap = mainObj.groupMoviesByCountAndFSKType(moviesList);
        System.out.println("Grouped movies by FSK Categories..total categories: "+ movieMap.size());
        System.out.println("All movies categories are : "+ movieMap.entrySet().toString());

        System.out.println("Writing output Json results...");
        mainObj.createMoviesOutputJson(movieMap);
        System.out.println("Successfully created result json at - ./result/result.json");

    }

    private void createMoviesOutputJson(Map<String, ArrayList<Movie>> movieMap) throws IOException {
        Gson gson = new Gson();
        JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(new File("./result/result.json"))));
        writer.beginArray();
        movieMap.forEach((k,v) -> {
            JsonElement element = gson.toJsonTree(new FskCategory(k, movieMap.get(k).size(), movieMap.get(k)), FskCategory.class);
            try {
                writer.jsonValue(gson.toJson(element));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.endArray();
        writer.close();
    }

    private Map<String, ArrayList<Movie>> groupMoviesByCountAndFSKType(List<Movies> moviesList) {
        Map<String, ArrayList<Movie>> movieMap = new HashMap<>();
        moviesList.forEach(m -> {
            String fskArray = String.join(",", m.getFsk_level_list_facet());
            movieMap.computeIfAbsent(fskArray, k -> new ArrayList<>());
            movieMap.get(fskArray).add(new Movie(m.getId(),
                    m.getTitle(),
                    m.getProduction_year()));
        });
        return movieMap;
    }

    private List<Movies> extractMoviesFromJson(File inputJsonFile) throws FileNotFoundException {
        Gson gson = new Gson();
        List<Movies> allAssets = gson.fromJson(new BufferedReader( new FileReader(inputJsonFile)), new TypeToken<List<Movies>>(){}.getType());
        return allAssets.stream().filter(m -> m.getObject_class().equalsIgnoreCase("Movie")).collect(Collectors.toList());
    }

    private File loadInputFile() {
        String fileName = "assets.json";
        ClassLoader classLoader = Main.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }


}
