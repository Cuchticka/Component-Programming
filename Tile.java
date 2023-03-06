public abstract class Tile {
    private TileState state = TileState.NOTSWAPPABLE;

    void setState(TileState state){
        this.state = state;
    }

    public TileState getState() {
        return state;
    }
}
