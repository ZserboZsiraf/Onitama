package logic;

/**
 * A Matchupok. 3 fajta van: Játékos a játékos ellen
 *                           Játékos a véletlenszerűen lépő gép ellen.
 *                           Játékos a MiniMax-es AI ellen.
 */
public enum Matchup {
    PlayerVsPlayer,
    PlayerVsRandom,
    PlayerVsAI
}
