import React from 'react';
import NaverLogin from 'react-naver-login';

const NaverLoginButton = () => {
  // 성공 핸들러
  const NaverOnSuccess = async (data) => {
    console.log('Login Success:', data);
    const idToken = data.response.access_token;
    // 추가 로직 처리 (예: API 호출 등)
  };

  // 실패 핸들러
  const NaverOnFailure = (error) => {
    console.error('Login Failed:', error);
  };

  return (
    <div>
      <NaverLogin
        token={process.env.REACT_APP_NAVER_CLIENT_ID}
        callbackUrl="http://127.0.0.1:3000/login" // 콜백 URL을 여기에 입력
        render={(props) => (
          <button onClick={props.onClick} style={{ padding: '10px', background: '#03C75A', color: 'white', border: 'none', cursor: 'pointer' }}>
            네이버로 로그인하기
          </button>
        )}
        onSuccess={NaverOnSuccess} // 성공 핸들러 연결
        onFailure={NaverOnFailure} // 실패 핸들러 연결
      />
    </div>
  );
};

export default NaverLoginButton;
