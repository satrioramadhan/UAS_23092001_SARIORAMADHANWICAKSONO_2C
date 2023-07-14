package GetJSON;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "https://dummyjson.com/products/category/smartphones";

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Cons-ID", "harber123");
            connection.setRequestProperty("X-userkey", "tabc4XbR");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray productsArray = jsonObject.getJSONArray("products");

                List<Model> models = new ArrayList<>();


                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject productObject = productsArray.getJSONObject(i);

                    int id = productObject.getInt("id");
                    String title = productObject.getString("title");
                    String description = productObject.getString("description");
                    double price = productObject.getDouble("price");
                    double discountPercentage = productObject.getDouble("discountPercentage");
                    double rating = productObject.getDouble("rating");
                    int stock = productObject.getInt("stock");
                    String brand = productObject.getString("brand");
                    String category = productObject.getString("category");
                    String thumbnail = productObject.getString("thumbnail");
                    JSONArray imagesArray = productObject.getJSONArray("images");
                    List<String> images = new ArrayList<>();
                    for (int j = 0; j < imagesArray.length(); j++) {
                        images.add(imagesArray.getString(j));
                    }

                    Model model = new Model(id, title, description, price, discountPercentage, rating, stock, brand, category, thumbnail, images);
                    models.add(model);
                }

                Collections.sort(models, (m1, m2) -> Integer.compare(m1.getId(), m2.getId()));

                for (Model model : models) {
                    System.out.println(model);
                }
            } else {
                System.out.println("Failed to retrieve data. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

