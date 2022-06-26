package logic;

import java.io.Serializable;

/**
 * A meccs állását szimbolizálja. Három lehetőség van: Piros nyert.
 *                                                     Kék nyert.
 *                                                     Még nincs eldöntve, hogy ki nyert.
 */
public enum Status implements Serializable {
    REDWON,
    BLUEWON,
    NOTYETDETERMINED
}
