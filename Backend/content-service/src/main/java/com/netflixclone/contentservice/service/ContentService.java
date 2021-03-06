package com.netflixclone.contentservice.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ContentService {

    @Value("${tmdb.api_key}")
    private String API_KEY;
    final static String TMDB_URL = "https://api.themoviedb.org/3/";

    private String FetchData(String url) throws IOException {
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            return response.toString();
        } else {
            System.out.println("GET NOT WORKED");
            return EmptyJson();
        }
    }

    private String EmptyJson() {
        JSONObject empty = new JSONObject();
        empty.put("page", 1);
        empty.put("results", new JSONArray());
        empty.put("total_pages", 0);
        empty.put("total_results", 0);
        return empty.toString();
    }

    public String FetchTrending() throws IOException {
        String path = TMDB_URL + "trending/all/week?api_key=" + API_KEY + "&language=en-US";
        return FetchData(path);
    }

    public String FetchOriginals() throws IOException {
        String path = TMDB_URL + "discover/tv?api_key=" + API_KEY + "&with_networks=213";
        return FetchData(path);
    }

    public String FetchTopRated() throws IOException {
        String path = TMDB_URL + "movie/top_rated?api_key=" + API_KEY + "&language=en-US";
        return FetchData(path);
    }

    public String FetchActionMovies() throws IOException {
        String path = TMDB_URL + "discover/movie?api_key=" + API_KEY + "&with_genres=28";
        return FetchData(path);
    }

    public String FetchComedyMovies() throws IOException {
        String path = TMDB_URL + "discover/movie?api_key=" + API_KEY + "&with_genres=35";
        return FetchData(path);
    }

    public String FetchHorrorMovies() throws IOException {
        String path = TMDB_URL + "discover/movie?api_key=" + API_KEY + "&with_genres=27";
        return FetchData(path);
    }

    public String FetchRomanceMovies() throws IOException {
        String path = TMDB_URL + "discover/movie?api_key=" + API_KEY + "&with_genres=10749";
        return FetchData(path);
    }

    public String FetchDocumentaryMovies() throws IOException {
        String path = TMDB_URL + "discover/movie?api_key=" + API_KEY + "&with_genres=99";
        return FetchData(path);
    }

}
