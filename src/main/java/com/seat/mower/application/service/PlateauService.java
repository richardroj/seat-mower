package com.seat.mower.application.service;

import com.seat.mower.MowerApplication;
import com.seat.mower.domain.Mower;
import com.seat.mower.domain.Plateau;
import com.seat.mower.domain.service.MowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PlateauService {

    @Autowired
    MowerService mowerService;


    private static Logger logger = Logger.getLogger(MowerApplication.class.toString());


    public Plateau createPlateau(Integer width, Integer height) {
        logger.info("Creating Plateau");
        return Plateau.builder()
                .id(UUID.randomUUID()
                        .toString())
                .width(width)
                .height(height)
                .build();
    }

    public Plateau executeCommands(Plateau plateau){
        logger.info("Executing Commands");
        List<Mower> mowers = new ArrayList<>();
        plateau.getMowers().forEach(mower -> {
            mowers.add(mowerService.move(mower, mower.getCommands()));
        });
        plateau.setMowers(mowers);

        return plateau;
    }

}
