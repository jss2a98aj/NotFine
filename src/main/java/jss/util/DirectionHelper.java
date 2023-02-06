package jss.util;

public class DirectionHelper {

    public static final Directions[] orderedDirections = {
        Directions.YNeg,
        Directions.YPos,
        Directions.ZNeg,
        Directions.ZPos,
        Directions.XNeg,
        Directions.XPos
    };

    public static final Directions[] oppositeDirections = {
        Directions.YPos,
        Directions.YNeg,
        Directions.ZPos,
        Directions.ZNeg,
        Directions.XPos,
        Directions.XNeg
    };

    public static final int[] yDirectionalIncrease = {
        -1,
        1,
        -0,
        0,
        -0,
        0
    };

    public static final int[] zDirectionalIncrease = {
        -0,
        0,
        -1,
        1,
        -0,
        0
    };

    public static final int[] xDirectionalIncrease = {
        -0,
        0,
        -0,
        0,
        -1,
        1
    };


    public static final Directions[] relatuveADirections = {
        Directions.ZNeg,
        Directions.ZPos,
        Directions.XNeg,
        Directions.XPos,
        Directions.YNeg,
        Directions.YPos
    };

    public static final Directions[] relatuveBDirections = {
        Directions.ZPos,
        Directions.ZNeg,
        Directions.XPos,
        Directions.XNeg,
        Directions.YPos,
        Directions.YNeg
    };

    public static final Directions[] relatuveCDirections = {
        Directions.XNeg,
        Directions.XPos,
        Directions.YNeg,
        Directions.YPos,
        Directions.ZNeg,
        Directions.ZPos
    };
    public static final Directions[] relatuveDDirections = {
        Directions.XPos,
        Directions.XNeg,
        Directions.YPos,
        Directions.YNeg,
        Directions.ZPos,
        Directions.ZNeg
    };

}
