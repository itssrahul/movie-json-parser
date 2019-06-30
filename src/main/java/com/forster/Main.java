package com.forster;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws IOException {
        Main mainObj = new Main();

        // loading input json...
        out.println("loading input json...");
        File inputJsonFile = mainObj.loadInputFile();
        out.println("loaded input json...");

        // Extracting movies from input json...
        out.println("Extracting movies from input json...");
        List<Movies> moviesList = mainObj.extractMoviesFromJson(inputJsonFile);
        out.println("Extracted movies from input json...; moviesCount : "+moviesList.size());

        // Grouping movies by FSK Categories..
        out.println("Grouping movies by FSK Categories..");
        Map<String, ArrayList<Movie>> movieMap = mainObj.groupMoviesByCountAndFSKType(moviesList);
        out.println("Grouped movies by FSK Categories..total categories: "+ movieMap.size());
        out.println("All movies categories are : "+ movieMap.entrySet().toString());


        // Writing output Json results...
        out.println("Writing output Json results...");
        mainObj.writeJsonToFile(mainObj.createCategorisedMoviesList(movieMap));
        out.println("Successfully created result json at - ./result/result.json");

    }

    private void writeJsonToFile(List<FskCategory> fskCategoryList) {
        try (FileWriter file = new FileWriter("./result/result.json")) {
            file.write(new Gson().toJson(fskCategoryList));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FskCategory> createCategorisedMoviesList(@NotNull Map<String, ArrayList<Movie>> movieMap) {
        return movieMap.keySet().stream().map(k -> new FskCategory(k, movieMap.get(k).size(), movieMap.get(k))).collect(Collectors.toList());
    }

    private Map<String, ArrayList<Movie>> groupMoviesByCountAndFSKType(@NotNull List<Movies> moviesList) {
        Map<String, ArrayList<Movie>> movieMap = new HashMap<>();
        moviesList.forEach(m -> {
            String fskArray = String.join(",", m.getFsk_level_list_facet());
            movieMap.computeIfAbsent(fskArray, k -> new ArrayList<>());
            movieMap.get(fskArray).add(new Movie(m.getId(), m.getTitle(), m.getProduction_year()));
        });
        return movieMap;
    }

    private List<Movies> extractMoviesFromJson(File inputJsonFile) throws FileNotFoundException {
        List<Movies> allAssets = new Gson().fromJson(new BufferedReader( new FileReader(inputJsonFile)), new TypeToken<List<Movies>>(){}.getType());
        return allAssets.stream().filter(m -> m.getObject_class().equalsIgnoreCase("Movie")).collect(Collectors.toList());
    }

    @NotNull
    private File loadInputFile() {
        String fileName = "assets.json";
        ClassLoader classLoader = Main.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }


}
