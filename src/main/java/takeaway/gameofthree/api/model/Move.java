package takeaway.gameofthree.api.model;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class Move {

    private String player;
    private Integer number;
    private Integer nextNumber;
    private Integer added;
    private boolean gameFinished;

    public Move() {
    }

    public Move(String player, Integer number, Integer nextNumber, Integer added, boolean gameFinished) {
        this.player = player;
        this.number = number;
        this.nextNumber = nextNumber;
        this.added = added;
        this.gameFinished = gameFinished;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Integer nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Integer getAdded() {
        return added;
    }

    public void setAdded(Integer added) {
        this.added = added;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((added == null) ? 0 : added.hashCode());
        result = prime * result + (gameFinished ? 1231 : 1237);
        result = prime * result + ((nextNumber == null) ? 0 : nextNumber.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;
        if (added == null) {
            if (other.added != null)
                return false;
        } else if (!added.equals(other.added))
            return false;
        if (gameFinished != other.gameFinished)
            return false;
        if (nextNumber == null) {
            if (other.nextNumber != null)
                return false;
        } else if (!nextNumber.equals(other.nextNumber))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }
}
