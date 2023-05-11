package QuizPackage;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Answer> answers = new ArrayList<Answer>();
        try {
            Answer a1 = new Answer("Errada 1");
            Answer a2 = new Answer("Errada 2");
            Answer a3 = new Answer("Certa");
            Answer a4 = new Answer("Errada 3");

            answers.add(a1);
            answers.add(a2);
            answers.add(a3);
            answers.add(a4);
            
            Question q1 = new Question("Pergunta 1", answers, 1);

            Quiz quiz = new Quiz("Meu Quiz");
            q1.setDescription("Pergunta 1 alterada");
            quiz.score();
            quiz.score();
            System.out.println(quiz.getScore());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }
}
