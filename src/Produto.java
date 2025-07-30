public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private String categoria;

    public Produto(int id, String descricao, double preco, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }


    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }


    @Override
    public String toString() {
        return String.format("ID: %d | %s - R$ %.2f | Categoria: %s",
                id, descricao, preco, categoria);
    }
}
