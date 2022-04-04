package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {

    TextView welcomeTextView;
    TextView titleTextView;
    TextView descTextView;
    Button answerOne;
    Button answerTwo;
    Button answerThree;
    Button submitBtn;
    public Integer clickedAnswer = 0;
    public Integer questionNum = 0;
    public Integer totalQuestions = 5;
    public Integer correctAnswers = 0;
    public Boolean submitted = false;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        progress = findViewById(R.id.progress_bar);

        welcomeTextView = findViewById(R.id.welcomeLabel);
        String text = welcomeTextView.getText().toString();
        welcomeTextView.setText(text + ' ' + name.toString());

        titleTextView = findViewById(R.id.questionTitleLabel);
        descTextView = findViewById(R.id.questionDescLabel);

        answerOne = findViewById(R.id.answer1);
        answerTwo = findViewById(R.id.answer2);
        answerThree = findViewById(R.id.answer3);

        Question[] questionsArr = getQuestions();

        titleTextView.setText(questionsArr[questionNum].title.toString());
        answerOne.setText(questionsArr[questionNum].answerOne.toString());
        answerTwo.setText(questionsArr[questionNum].answerTwo.toString());
        answerThree.setText(questionsArr[questionNum].answerThree.toString());

        submitBtn = findViewById(R.id.btnSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!submitted) {
                    Integer answer = questionsArr[questionNum].answer;
                    String msg = "";
                    if (clickedAnswer == 0) {
                        msg = "Please select an answer";
                    } else if (clickedAnswer == answer) {
                        msg = "Well done, you are correct!";
                        correctAnswers++;
                    }
                    else {
                        msg = "Better luck next time!";
                    }

                    if (clickedAnswer > 0) {
                        if (clickedAnswer == 1) {
                            answerOne.setBackgroundColor(getResources().getColor(R.color.red));
                        } else if (clickedAnswer == 2) {
                            answerTwo.setBackgroundColor(getResources().getColor(R.color.red));
                        } else {
                            answerThree.setBackgroundColor(getResources().getColor(R.color.red));
                        }

                        if (answer == 1) {
                            answerOne.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        } else if (answer == 2) {
                            answerTwo.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        } else {
                            answerThree.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        }
                        submitted = true;
                        progress.incrementProgressBy(20);
                        submitBtn.setText(R.string.next_button_label);
                        descTextView.setText(questionsArr[questionNum].desc.toString());
                    }
                    Toast.makeText(Questions.this, msg, Toast.LENGTH_SHORT ).show();
                } else {
                    clickedAnswer = 0;
                    questionNum++;
                    if (questionNum >= totalQuestions) {
                        Intent intent = new Intent(getApplicationContext(), Result.class);
                        intent.putExtra("answers", correctAnswers.toString());
                        intent.putExtra("name", name);
                        startActivityForResult(intent, 1);
                        finish();
                    } else {
                        submitted = false;
                        descTextView.setText(R.string.empty_string);
                        titleTextView.setText(questionsArr[questionNum].title.toString());
                        answerOne.setText(questionsArr[questionNum].answerOne.toString());
                        answerOne.setBackgroundColor(getResources().getColor(R.color.purple_500));
                        answerTwo.setText(questionsArr[questionNum].answerTwo.toString());
                        answerTwo.setBackgroundColor(getResources().getColor(R.color.purple_500));
                        answerThree.setText(questionsArr[questionNum].answerThree.toString());
                        answerThree.setBackgroundColor(getResources().getColor(R.color.purple_500));
                        submitBtn.setText(getResources().getText(R.string.submit_button_label));
                    }
                }
            }
        });
    }

    public void answerOneClick(View view) {
        if (!submitted) {
            if (clickedAnswer == 1) {
                clickedAnswer = 0;
                answerOne.setBackgroundColor(getResources().getColor(R.color.purple_500));
            } else {
                clickedAnswer = 1;
                answerOne.setBackgroundColor(getResources().getColor(R.color.teal_700));
                answerTwo.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answerThree.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }
        }
    }

    public void answerTwoClick(View view) {
        if (!submitted) {
            if (clickedAnswer == 2) {
                clickedAnswer = 0;
                answerTwo.setBackgroundColor(getResources().getColor(R.color.purple_500));
            } else {
                clickedAnswer = 2;
                answerOne.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answerTwo.setBackgroundColor(getResources().getColor(R.color.teal_700));
                answerThree.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }
        }
    }

    public void answerThreeClick(View view) {
        if (!submitted) {
            if (clickedAnswer == 3) {
                clickedAnswer = 0;
                answerThree.setBackgroundColor(getResources().getColor(R.color.purple_500));
            } else {
                clickedAnswer = 3;
                answerOne.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answerTwo.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answerThree.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        }
    }

    public Question[] getQuestions() {
        Question[] questionsArr;
        questionsArr = new Question[totalQuestions];

        questionsArr[0] = new Question(
                "In which year did Melbourne host the Summer Olympics?",
                "This was the first summer Olympics held in Oceania.",
                "1934",
                "1956",
                "1981",
                2
        );

        questionsArr[1] = new Question(
                "The first Australian Parliament was held in which building?",
                "This was the site of the first Australian Federal Parliament in 1901. In 1927 Canberra was set up as a compromise location between Melbourne and Sydney.",
                "Victorian Parliament Building",
                "Melbourne Town Hall",
                "Royal Exhibition Building",
                3
        );

        questionsArr[2] = new Question(
                "A Brighton Beach Bathing Box sold for how much in 2016?",
                "Pretty good for a one-room house with no plumbing or windows.",
                "$326k",
                "$510k",
                "$120k",
                1
        );

        questionsArr[3] = new Question(
                "Which person designed the Melbourne CBD grid?",
                "Robert Hoddle's first map of Melbourne, completed on 25 March 1837, covered the area from Flinders Street to Lonsdale  Street, and from Spencer Street to Spring Street.",
                "Robert Hoddle",
                "John Monash",
                "Charles La Trobe",
                1
        );

        questionsArr[4] = new Question(
                "Which iconic Melbourne business opened first?",
                "The Mitre Tavern began operating as a pub in 1868. The Hopetoun Tea Rooms opened in 1891, the Windsor Hotel in 1883.",
                "Hopetoun Tea Rooms",
                " Windsor Hotel",
                "The Mitre Tavern",
                3
        );
        return questionsArr;
    }

    class Question {
        public String title;
        public String desc;
        public String answerOne;
        public String answerTwo;
        public String answerThree;
        public Integer answer;

        Question(String title, String desc, String answerOne,
                 String answerTwo, String answerThree,
                 Integer answer)
        {
            this.title = title;
            this.desc = desc;
            this.answerOne = answerOne;
            this.answerTwo = answerTwo;
            this.answerThree = answerThree;
            this.answer = answer;
        }
    }
}

