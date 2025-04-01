import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static  void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Endereco> enderecos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        while(!busca.equalsIgnoreCase("sair")){
            System.out.println("Digite um CEP para busca: ");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://viacep.com.br/ws/" + busca + "/json/";

            try{
                HttpClient client = HttpClient.newBuilder()
                        .build();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                Endereco meuEndereco = gson.fromJson(json, Endereco.class);
                System.out.println(meuEndereco);
                enderecos.add(meuEndereco);


            }catch (IOException | InterruptedException e) {
                System.out.println("Ocorreu um erro ao fazer a requisição: " + e.getMessage());
            }
        }
        try (FileWriter escrita = new FileWriter("enderecos.json")) {
            gson.toJson(enderecos, escrita);
            System.out.println("Endereços escritos com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}