package QuizPackage;

public class Answer {
    private String description;

    Answer(String description) throws Exception{
        if (description != null)
            setDescription(description);
        else
            throw new Exception("Não é possível criar uma resposta sem descrição.");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
