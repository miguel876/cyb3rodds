package enterprise.minura.cyb3rodds.model;

/**
 * Created by Utilizador on 22-11-2017.
 */

public class UserWallet {
    int id_utilizador, cyb3r, money;

    public int getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    public int getCyb3r() {
        return cyb3r;
    }

    public void setCyb3r(int cyb3r) {
        this.cyb3r = cyb3r;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public UserWallet(int id_utilizador, int cyb3r, int money) {

        this.id_utilizador = id_utilizador;
        this.cyb3r = cyb3r;
        this.money = money;
    }
}
