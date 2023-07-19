import React, { useEffect, useState } from "react";
import { UserOutlined } from "@ant-design/icons";
import {
  Layout,
  Menu,
  theme,
  Breadcrumb,
  Button,
  Avatar,
  Dropdown,
  Space,
  Form,
  Modal,
  Input,
} from "antd";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";
import Content from "../../components/admin/Content";

const { Header, Sider } = Layout;

function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}

const items = [
  {
    label: <button style={{
      background: "none",
      border: "none",
      padding: 0,
      cursor: "pointer",
    }} href="#/">Edit Profile</button>,
    key: "0",
  },
  {
    label: <button
    style={{
      background: "none",
      border: "none",
      padding: 0,
      cursor: "pointer",
    }}
    // onClick={showModal}
  >
    Change Password
  </button>,
    key: "1",
  },
];

const UserProfile = () => {
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const navigate = useNavigate();

  const [userName, setUserName] = useState("");
  const [id, setId] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [form] = Form.useForm();

  useEffect(() => {
    const token = localStorage.getItem("token");

    // const parsedToken = JSON.parse(token);
    // const isAdmin = token && token.includes("ROLE_ADMIN");
    // const storedUserName = JSON.parse(token).userName;

    const isAdmin = JSON.parse(token).roleNames.includes("ROLE_ADMIN");

    if (!isAdmin) {
      navigate("/admin/login");
    } else {
      setUserName(JSON.parse(token).userName);
      setId(JSON.parse(token).id);
    }
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/admin/login");
  };

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    form.validateFields().then((values) => {
      form.resetFields();
      setIsModalOpen(false);
      // Do something with the new password and confirm new password values
      console.log("New Password:", values.newPassword);
      console.log("Confirm New Password:", values.confirmNewPassword);
    });
  };

  const handleCancel = () => {
    form.resetFields();
    setIsModalOpen(false);
  };


  return (
    <Layout>
      <Header
        style={{
          display: "flex",
          alignItems: "center",
          position: "sticky",
          top: 0,
          zIndex: 1,
          width: "100%",
        }}
      >
        <div className="demo-logo" />
        <div
          style={{ marginLeft: "auto", display: "flex", alignItems: "center" }}
        >
          <Dropdown
            menu={{
              items,
            }}
            trigger={["click"]}
          >
            <button
              style={{
                background: "none",
                border: "none",
                padding: 0,
                cursor: "pointer",
              }}
              onClick={(e) => e.preventDefault()}
            >
              <Space>
                <Avatar
                  style={{
                    backgroundColor: "#87d068",
                  }}
                  icon={<UserOutlined />}
                />
                <span style={{ color: "#fff", marginRight: 20 }}>
                  {userName}
                </span>
              </Space>
            </button>
          </Dropdown>
          <Button
            type="text"
            onClick={handleLogout}
            style={{
              color: "#fff",
              display: "flex",
              alignItems: "center",
              marginLeft: "auto",
            }}
          >
            <span className="material-symbols-outlined">logout</span>
          </Button>
        </div>
      </Header>
      <Layout>
        <Sider
          width={250}
          style={{
            background: colorBgContainer,
            height: "100vh",
          }}
        >
          <Menu
            mode="inline"
            defaultSelectedKeys={["1"]}
            defaultOpenKeys={["sub1"]}
            style={{
              height: "100%",
              borderRight: 0,
            }}
            items={[
              getItem(
                <a
                  href="/admin/dashboard"
                  style={{ display: "flex", alignItems: "center" }}
                >
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    dashboard
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>
                    Dashboard
                  </p>
                </a>,
                "sub1"
              ),
              {
                type: "divider",
              },
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    book
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Books</p>
                </a>,
                "sub2"
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    contact_page
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Author</p>
                </a>,
                "sub3"
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    category
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>
                    Category
                  </p>
                </a>,
                "sub4"
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    folder_shared
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Users</p>
                </a>,
                "sub5",
                null,
                [
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        assignment_ind
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        User Profile
                      </p>
                    </a>,
                    "6"
                  ),
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        edit
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        User Edit
                      </p>
                    </a>,
                    "7"
                  ),
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        person_add
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        User Add
                      </p>
                    </a>,
                    "8"
                  ),
                ]
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    person_apron
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Staff</p>
                </a>,
                "sub6",
                null,
                [
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        assignment_ind
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        Staff Profile
                      </p>
                    </a>,
                    "9"
                  ),
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        edit
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        Staff Edit
                      </p>
                    </a>,
                    "10"
                  ),
                  getItem(
                    <a
                      href="#/"
                      style={{ display: "flex", alignItems: "center" }}
                    >
                      <span
                        className="material-symbols-outlined"
                        style={{ marginRight: 10 }}
                      >
                        person_add
                      </span>
                      <p style={{ fontSize: "16px", fontWeight: "300" }}>
                        Staff Add
                      </p>
                    </a>,
                    "11"
                  ),
                ]
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    receipt_long
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Invoice</p>
                </a>,
                "sub7"
              ),
              getItem(
                <a href="#/" style={{ display: "flex", alignItems: "center" }}>
                  <span
                    className="material-symbols-outlined"
                    style={{ marginRight: 10 }}
                  >
                    settings
                  </span>
                  <p style={{ fontSize: "16px", fontWeight: "300" }}>Setting</p>
                </a>,
                "sub8"
              ),
            ]}
          />
        </Sider>
        <Layout
          style={{
            padding: "0 24px 24px",
          }}
        >
          <Breadcrumb
            style={{
              margin: "16px 0",
            }}
            items={[
              {
                title: "Admin",
              },
              {
                title: <a href="/admin/dashboard">Dashboard</a>,
              },
            ]}
          />
          <Content />
        </Layout>
      </Layout>
      
      <Modal
        title="Change Password"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form form={form}>
          <Form.Item
            label="New Password"
            name="newPassword"
            rules={[
              {
                required: true,
                message: "Please enter a new password",
              },
            ]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item
            label="Confirm New Password"
            name="confirmNewPassword"
            dependencies={["newPassword"]}
            rules={[
              {
                required: true,
                message: "Please confirm the new password",
              },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue("newPassword") === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(
                    new Error("The passwords do not match")
                  );
                },
              }),
            ]}
          >
            <Input.Password />
          </Form.Item>
        </Form>
      </Modal>

    </Layout>
  );
};

export default UserProfile;
