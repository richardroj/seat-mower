package com.seat.mower;

import com.seat.mower.application.service.MowerAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class MowerApplication implements CommandLineRunner {

    @Autowired
    MowerAppService mowerAppService;
    private static Logger logger = Logger.getLogger(MowerApplication.class.toString());

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0) {
            var plateau = mowerAppService.createPlateauFromFile(args[0]);
            var updatedPlateau = mowerAppService.executeCommands(plateau);

            updatedPlateau.getMowers()
                    .forEach(mower -> {
                        logger.info("" + mower);
                    });
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MowerApplication.class, args);
    }

}
