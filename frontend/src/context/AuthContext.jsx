import React, { createContext,  useState, useEffect } from 'react'

//AuthCOntext 생성
export const AuthContext = createContext(null);

//AuthProvider 구현
export const AuthProvider = ({ children }) => {

    const [user, setUser] = useState('홍길동'); //사용자 정보
    const [token, setToken] = useState(null);
    const [isAuthenticated, setIsAuthenticated] = useState(null);
    const [role, setRole] = useState('guest'); //권한- guest, user, admin

    //로그인 상태 구현
    useEffect(()=>{
    const storedUser = localStorage.getItem('user');
    const storedToken = localStorage.getItem('token');

        if(storedUser && storedUser.email && storedToken) {
            const parasedUser = JSON.parse(storedUser);
            setUser(parasedUser);
            setToken(true);
            setIsAuthenticated(true);
            setRole(parasedUser.email === 'gksmsk5094@gmail.com' ? 'admin' : 'user');  //관리자 권한 부여
        }
    }, []);

    const login = ({userInfo, token}) => {
        setUser(userInfo);
        setToken(token);
        setIsAuthenticated(true);
        setRole(user.email === 'gksmsk5094@gmail.com' ? 'admin':'user');
        localStorage.setItem('user', JSON.stringify(user)); //로컬스토리지에 저장
        localStorage.setItem('token', token);
    }

    const logout = () => {
        setUser(null);
        setToken(null);
        setIsAuthenticated(false);
        setRole('guest');
        localStorage.removeItem('user'); 
        localStorage.removeItem('token');
    }

    return (
        <AuthContext.Provider value={{ user, token, isAuthenticated, role, login, logout }}>
            {children}
        </AuthContext.Provider>
    )
}