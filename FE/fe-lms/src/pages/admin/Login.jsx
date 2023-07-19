import React from "react";
// import { Button, Checkbox, Form, Input, Row, Col } from "antd";
import "./Login.css"; 
import LoginForm from "../../components/LoginForm";

export default function Login() {
  const onFinish = () => {
    // console.log("Success:", values);
  };

  const onFinishFailed = () => {
    // console.log("Failed:", errorInfo);
  };

  return (
    <div className="container">
      <div className="background-image" />
      <div className="login-form">
        <div className="login-form-heading">
          <h4>Welcome back!</h4>
        </div>
        <LoginForm
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        />
      </div>
      </div>
  );
}
