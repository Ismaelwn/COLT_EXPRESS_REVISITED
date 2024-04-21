package Modele.ActionJeu;

import Modele.EntiteJeu.Bandit;
import Modele.EntiteJeu.Direction;

public abstract class Action {
    protected Bandit k;
    protected Direction d;
    public abstract void executer();
    public abstract String toString();
    public Action( Bandit z, Direction c) {
        k = z;
        d = c;
    }

    public Bandit getk() {
        return k;
    }

    public Direction getd() {
        return d;
    }
}
