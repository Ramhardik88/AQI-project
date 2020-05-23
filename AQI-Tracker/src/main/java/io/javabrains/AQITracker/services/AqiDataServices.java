package io.javabrains.AQITracker.services;

import io.javabrains.AQITracker.model.ComponentStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class AqiDataServices {
    private static String AQI_Data_Url = "https://raw.githubusercontent.com/Ramhardik88/DATA_STRUCTURE/master/data.csv";
    private List<ComponentStats>  allStats=new ArrayList<>();

    public List<ComponentStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchAqiData() throws IOException, InterruptedException {

        List <ComponentStats> newStats =new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AQI_Data_Url))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //    System.out.println(httpResponse.body());


        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
//            String id = record.get("London Mean Background PM10 Particulate (ug/m3)");
//            System.out.println(id);

            ComponentStats componentStats= new ComponentStats();
            componentStats.setMonth(record.get("Month (text)"));
            componentStats.setGMT(record.get("GMT"));
            componentStats.setNitric_Oxide(record.get("London Mean Roadside Nitric Oxide (ug/m3)"));

            componentStats.setNitrogen_Dioxide(record.get("London Mean Roadside Nitrogen Dioxide (ug/m3)"));
            componentStats.setNitrogen(record.get("London Mean Roadside Oxides of Nitrogen (ug/m3)"));
            componentStats.setOzone(record.get("London Mean Roadside Ozone (ug/m3)"));
            componentStats.setPM10(record.get("London Mean Roadside PM10 Particulate (ug/m3)"));
            componentStats.setPM2_5(record.get("London Mean Roadside PM2.5 Particulate (ug/m3)"));
            componentStats.setSulphur_Dioxide(record.get("London Mean Roadside Sulphur Dioxide (ug/m3)"));

            System.out.println(componentStats);
            newStats.add(componentStats);
        }
        this.allStats=newStats;
}


}
