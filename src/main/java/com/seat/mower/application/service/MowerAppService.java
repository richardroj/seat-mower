package com.seat.mower.application.service;

import com.seat.mower.domain.Command;
import com.seat.mower.domain.Mower;
import com.seat.mower.domain.Orientation;
import com.seat.mower.domain.Plateau;
import com.seat.mower.domain.service.MowerService;
import com.seat.mower.insfraestructure.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MowerAppService {

    private final PlateauService plateauService;

    @Autowired
    MowerService mowerService;

    @Autowired
    FileService fileService;


    private static Logger logger = Logger.getLogger(MowerAppService.class.toString());


    public Plateau createPlateauFromFile(String urlFile) {
        logger.info("Creating plateau with file: " + urlFile);
        var fileLines = fileService.readFileAndGetCommands(urlFile);
        var plateau = convertLinesToPlateau(fileLines);

        return plateau;
    }

    public Plateau executeCommands(Plateau plateau){
        return plateauService.executeCommands(plateau);
    }

    public Plateau convertLinesToPlateau(List<String> lines) {
        logger.info("Processing Lines");
        if (lines.size() == 0)
            throw new IllegalArgumentException("Not Lines to process");

        var valuesXY = lines.get(0)
                .split(" ");
        var valueMaxX = getValueMax(valuesXY, 0);
        var valueMaxY = getValueMax(valuesXY, 1);
        Plateau plateau = plateauService.createPlateau(valueMaxX, valueMaxY);
        var mowers = new ArrayList<Mower>();
        var index = 1;
        //From line 2 to get values for each mower
        while (index < lines.size()) {
            var mower = createMowerWithPointAndOrientationFromLine(lines.get(index));
            index++;
            var commands = convertMovesStringToMovesList(lines.get(index));
            mower.setCommands(commands);
            mowers.add(mower);
            index++;
        }
        plateau.setMowers(mowers);

        return plateau;
    }

    private Mower createMowerWithPointAndOrientationFromLine(String line) {
        var positions = line.split(" ");
        if (positions.length < 3)
            throw new IllegalArgumentException("Not valid position, Expected 2 numbers and 1 Letter for direction");

        var point = new Point();
        point.setLocation(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));

        return mowerService.createMower(point, convertCharToDirection(positions[2].charAt(0)));

    }

    private Integer getValueMax(String[] valuesXY, int x) {
        return Integer.valueOf(valuesXY[x]);
    }

    private static List<Command> convertMovesStringToMovesList(String moves) {
        var listMoves = moves.toCharArray();
        var commandsList = new ArrayList<Command>();
        for (char listMove : listMoves) {
            switch (listMove) {
                case 'L':
                    commandsList.add(Command.L);
                    break;
                case 'R':
                    commandsList.add(Command.R);
                    break;
                case 'M':
                    commandsList.add(Command.M);
                    break;
                default:
                    throw new IllegalArgumentException("Command not accepted");
            }

        }
        return commandsList;
    }

    private static Orientation convertCharToDirection(char charValue) {

        Orientation orientation;
        switch (charValue) {
            case 'S':
                orientation = Orientation.S;
                break;
            case 'N':
                orientation = Orientation.N;
                break;
            case 'E':
                orientation = Orientation.E;
                break;
            case 'W':
                orientation = Orientation.W;
                break;
            default:
                throw new IllegalArgumentException("Orientation value not valid");
        }

        return orientation;
    }

}
