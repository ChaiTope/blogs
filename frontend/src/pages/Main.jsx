import React, { useState, useEffect } from 'react'
import { Container, Row, Col, Image } from 'react-bootstrap'
import { boxShadow, padding16, borders } from '../style/style'
import Banner from '../components/Banner'
import axios from 'axios'

const Main = () => {
  const [posts, setPosts] = useState([]);
    useEffect(()=>{
      axios.get('/api/posts')
           .then(response => {
              setPosts(response.data);
           })
           .catch(error=>console.error(error));
    },[]);
    
    //이미지태그 제거함수
    const removeImgTags = (content) => {
      return content.replace(/<img[^>]*>/g,'');
    };
  return (
    <Container style={{...boxShadow,...padding16,...borders}}>
       <Banner />
       <Row className="mt-4">
        {
          posts.map((post, index) => {
            (index === 0) ?
            <Col md="12" key={index}>
              {
                post.firstImg && (
                  <Image src={`upload/images/${post.ntime}/${post.firstImg}`} alr={post.title} />
                )
              }
              <h3>{post.title}</h3>
              <div dangerouslySetInnerHTML={{__html: removeImgTags(post.content)}} />
            </Col>
            :
            <Col md="6" key={index}>
              {
                post.firstImg && (
                    <Image src={`upload/images/${post.ntime}/${post.firstImg}`} fluid alt={post.title} />
                )
              }
              <h3>{post.title}</h3>
              <div dangerouslySetInnerHTML={{ __html: removeImgTags(post.content)}} />
            </Col> 
          })
        }
       </Row>
    </Container>
  )
}

export default Main