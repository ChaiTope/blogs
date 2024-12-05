import React, { useState } from "react";
import emailjs from "emailjs-com";
import '../style/ContactForm.css'

const Contact = () => {
  const [formData, setFormData] = useState({
    from_name: "", // 보낸 사람 이름
    to_name: "관리자", // 받는 사람 이름 (고정 값)
    title: "",
    message: "", // 메시지
  });
  
  const [isSent, setIsSent] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    emailjs
      .send(
        "", // EmailJS 서비스 ID
        "", // EmailJS 템플릿 ID
        formData, // 템플릿과 일치하는 데이터 전송
        "" // EmailJS 사용자 ID
      )
      .then(
        () => {
          setIsSent(true); // 성공 메시지 표시
          setFormData({ from_name: "", to_name: "관리자", title: "", message: "" }); // 폼 리셋
        },
        (error) => {
          alert("전송에 실패했습니다. 다시 시도해주세요.");
          console.error(error);
        }
      );
  };
  

  return (
    <div className="contact-form">
      <h2>💌 문의하기</h2>
      {isSent && <p className="success-message">메시지가 성공적으로 전송되었습니다! 🎉</p>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <input
            type="text"
            name="from_name"
            placeholder="이름"
            value={formData.from_name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <input
            name="title"
            placeholder="제목"
            value={formData.title}
            onChange={handleChange}
            required
          ></input>
        </div>
        <div className="form-group">
          <textarea
            name="message"
            placeholder="메시지"
            value={formData.message}
            onChange={handleChange}
            required
          ></textarea>
        </div>
        <button type="submit" className="btn-submit">
          보내기 🚀
        </button>
      </form>
    </div>
  );
};

export default Contact;
