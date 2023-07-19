import React from "react";
import { Button, Checkbox, Form, Input, Row, Col, notification } from "antd";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function LoginForm({ onFinish, onFinishFailed }) {

  const navigate = useNavigate();
  const [api, contextHolder] = notification.useNotification();

  const openErrorNotification = (type, message, description) => {
    api[type]({
      message: message,
      description: description,
    });
  };

  const handleSubmit = async (values) => {
    console.log("Success:", values);
    try {
      const res = await axios.post(
        "http://localhost:8080/api/backend/user/login",
        values
      );

      if (res.data.message === "OK") {
        const isAdmin = res.data.data.roleNames.includes("ROLE_ADMIN");
        if (!isAdmin) {
          openErrorNotification(
            "warning",
            "Login Failed",
            "You are not admin."
          );
        } else {
          localStorage.setItem("token",JSON.stringify(res.data.data)) ;
          navigate("/admin/dashboard");
          onFinish();
        }
      } else {
        openErrorNotification(
          "error",
          "Login Failed",
          "Invalid email or password. Please try again."
        );
      }
    } catch (error) {
      openErrorNotification(
        "error",
        "Login Failed",
        "Invalid email or password. Please try again."
      );
    }
  };

  return (
    <>
      {contextHolder}
      <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        initialValues={{ remember: true }}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
        onFinish={handleSubmit}
      >
        <Row justify="center">
          <Col xs={24} sm={24} md={8} lg={12} xl={12}>
            <Form.Item
              label="Email"
              name="email"
              rules={[{ required: true, message: "Please input your email!" }]}
              className="login-form-label"
            >
              <Input size="large" style={{ width: "100%" }} />
            </Form.Item>

            <Form.Item
              label="Password"
              name="password"
              rules={[
                { required: true, message: "Please input your password!" },
              ]}
              className="login-form-label"
            >
              <Input.Password size="large" />
            </Form.Item>

            <Form.Item wrapperCol={{ span: 24 }}>
              <Row justify="space-between" align="middle">
                <Col>
                  <Form.Item name="remember" valuePropName="checked" noStyle>
                    <Checkbox>Remember me</Checkbox>
                  </Form.Item>
                </Col>
                <Col>
                  <a
                    href="https://google.com"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Forget password
                  </a>
                </Col>
              </Row>
            </Form.Item>

            <Form.Item wrapperCol={{ span: 24 }}>
              <Button
                type="primary"
                htmlType="submit"
                className="login-form-submit"
                size="large"
                block
              >
                Login
              </Button>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </>
  );
}
