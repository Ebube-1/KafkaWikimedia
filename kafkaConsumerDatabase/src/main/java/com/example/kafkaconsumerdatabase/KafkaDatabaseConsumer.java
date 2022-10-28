package com.example.kafkaconsumerdatabase;

import com.example.kafkaconsumerdatabase.entity.WikimediaData;
import com.example.kafkaconsumerdatabase.repository.WikimediaDataRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDatabaseConsumer {

     private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

     private final WikimediaDataRepository wikimediaDataRepository;

     @KafkaListener(topics = "wikimedia_RecentChange", groupId = "myGroup")
     public void consume(String eventMessage){
          LOGGER.info(String.format("Event message received: %s", eventMessage));
          WikimediaData wikimediaData = new WikimediaData();
          wikimediaData.setWikiEventData(eventMessage);

          wikimediaDataRepository.save(wikimediaData);


     }


}
