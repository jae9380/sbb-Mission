package com.example.sbbmissoin;

import com.example.sbbmissoin.answer.entity.Answer;
import com.example.sbbmissoin.answer.repository.AnswerRepository;
import com.example.sbbmissoin.question.entity.Question;
import com.example.sbbmissoin.question.repository.QuestionRepository;
import com.example.sbbmissoin.question.service.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbMissoinApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;
    @Test
    void testJpa1() {
        Question question1 =new Question();
        question1.setSubject("ssb가 무엇인가요?");
        question1.setContent("ssb에 대해서 알고 싶습니다.");
        question1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question1);

        Question question2 =new Question();
        question2.setSubject("스프링부트 모델 질문입니다.");
        question2.setContent("id는 자동으로 생성되나요?");
        question2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question2);
    }

    @DisplayName("findAll")
    @Test
    void testJpa2(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("ssb가 무엇인가요?", q.getSubject());
    }

    @DisplayName("findById")
    @Test
    void testJpa3(){
        Optional<Question> oq= this.questionRepository.findById(1);
        if (oq.isPresent()){
            Question q=oq.get();
            assertEquals("ssb가 무엇인가요?",q.getSubject());
        }
    }

    @DisplayName("findBySubject")
    @Test
    void testJpa4(){
        Question q =this.questionRepository.findBySubject("ssb가 무엇인가요?");
        assertEquals(1,q.getId());
    }

    @DisplayName("findBySubjectAndContent")
    @Test
    void testJpa5(){
        Question q=this.questionRepository.findBySubjectAndContent(
                "ssb가 무엇인가요?", "ssb에 대해서 알고 싶습니다.");
        assertEquals(1,q.getId());
    }

    @DisplayName("findBySubjectLike")
    @Test
    void testJpa6(){
        List<Question> qList = this.questionRepository.findBySubjectLike("ssb%");
        Question q = qList.get(0);
        assertEquals("ssb가 무엇인가요?",q.getSubject());
    }

    @DisplayName("데이터 수정")
    @Test
    void testJpa7(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @DisplayName("데이터 삭제")
    @Test
    void testJpa8(){
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @DisplayName("Answer데이터 생성, 저장")
    @Test
    void testJpa9(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @DisplayName("답변 조회")
    @Test
    void testJpa10(){
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @DisplayName("답변의 질문 찾기, 질문의 답변 찾기")
    @Transactional
    @Test
    void testJpa11(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        List<Answer> answerList = q.getAnswerList();
        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

    @DisplayName("대량 테스트 데이터")
    @Test
    void tj1(){
        for (int i = 0; i <300 ; i++) {
            String subject= String.format("테스트 데이터:[%03d]",i);
            String content= "내용";
            this.questionService.create(subject,content);
        }
    }
}
