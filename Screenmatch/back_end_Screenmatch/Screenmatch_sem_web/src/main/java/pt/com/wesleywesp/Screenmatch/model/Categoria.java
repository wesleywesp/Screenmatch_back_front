package pt.com.wesleywesp.Screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    DRAMA("Drama", "Drama"),
    COMEDIA("Comedy", "comédia"),
    ROMANCE("Romance", "Romance"),
    CRIME("Crime", "Crime"),
    ANIMACAO("Animation", "Animação"),
    TERROR("Horror", "terror"),
    AVENTURA("Adventure", "Aventura");

    private String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues= categoriaPortugues;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
