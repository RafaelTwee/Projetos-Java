package QuizPackage;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String description;
    private List<Answer> answers = new ArrayList<>();
    private int rightAnswer;

    Question (String description, List<Answer> answers, int rightAnswer) throws Exception {
        if (description == null)
            throw new Exception("Não é possível criar uma pergunta sem descrição.");
        else if (answers.size() != 4)    
            throw new Exception("A lista de respostas não possui 4 respostas.");
        else if (rightAnswer < 1 || rightAnswer > 4)
            throw new Exception("A resposta correta deve estar entre 1 e 4. ");
        else {
            setDescription(description);
            this.answers = answers;
            this.rightAnswer = rightAnswer;
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int position) {
        return answers.get(position - 1);
    }

    public void setRightAnswer(int number) throws Exception {
        if (number > 0 && number < 5)
            rightAnswer = number;
        else
            throw new Exception("A alternativa deve estar entre 1 e 4");
    }

    public boolean checkRightAnswer(int number) {
        if (rightAnswer == number)
            return true;
        else
            return false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description +"\n"+ answers.get(0).getDescription() +"\n"+ answers.get(1).getDescription() +"\n"+answers.get(2).getDescription() +"\n"+answers.get(3).getDescription();
    }
    
}
