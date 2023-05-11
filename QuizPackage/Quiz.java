package QuizPackage;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String title;
    private int score = 0;
    private List<Question> questions = new ArrayList<>();

    public Quiz(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int position) {
        return questions.get(position - 1);
    }

    public void addQuestion(Question question) throws Exception {
        if (questions.size() <= 10)
            questions.add(question);
        else
            throw new Exception("O quiz já possui 10 perguntas. ");

    }
    
    public void score() {
        score++;
    }

    public int getScore() {
        return score;
    }

}
