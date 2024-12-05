import React, { useState, useEffect } from "react";
import { Container, Row, Col, Card, Spinner, Alert } from "react-bootstrap";
import axios from "axios";

const Git = () => {
  const [repo, setRepo] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [languages, setLanguages] = useState(null);

  const getRepos = async () => {
    setLoading(true);
    setError(null);
    try {
      const token = "your_personal_access_token"; // 여기에 토큰 입력
      const res = await axios.get("https://api.github.com/users/ChaiTope/repos", {
        headers: {
          Authorization: `token ${token}`,
        },
      });
      const langs = Array.from(new Set(res.data.map((repo) => repo.language).filter(Boolean)));
      setRepo(res.data);
      setLanguages(langs);
    } catch (err) {
      setError("에러가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getRepos();
  }, []);

  return (
    <Container>
      <Row className="my-4">
        <Col>
          <h2 className="text-center">GitHub Repositories</h2>
        </Col>
      </Row>
      {loading && (
        <Row className="justify-content-center">
          <Spinner animation="border" role="status">
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </Row>
      )}
      {error && (
        <Row className="justify-content-center">
          <Alert variant="danger">{error}</Alert>
        </Row>
      )}
      <Row xs={1} sm={2} md={3} lg={4} className="g-4">
        {repo.map((r) => (
          <Col key={r.id}>
            <Card
              className="h-100 clickable-card"
              onClick={() => window.open(r.html_url, "_blank", "noopener noreferrer")}
            >
              <Card.Body>
                <Card.Title>{r.name}</Card.Title>
                <Card.Text>{r.description || "No description available."}</Card.Text>
              </Card.Body>
              <Card.Footer>
                {r.language || "Unknown Language"}
              </Card.Footer>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Git;
