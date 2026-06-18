import { useContext, useEffect, useState } from "react";
import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router";
import {
  Box,
  Button,
  Typography,
  AppBar,
  Toolbar,
  Container,
} from "@mui/material";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

const ActvitiesPage = () => {
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  return (
    <Container maxWidth="lg" sx={{ mt: 4 }}>
      <ActivityForm
        onActivityAdded={() => setRefreshTrigger((prev) => prev + 1)}
      />
      <ActivityList refreshTrigger={refreshTrigger} />
    </Container>
  );
};

function App() {
  const { token, tokenData, logIn, logOut } = useContext(AuthContext);
  const dispatch = useDispatch();

  useEffect(() => {
    if (token) {
      dispatch(setCredentials({ token, user: tokenData }));
    }
  }, [token, tokenData, dispatch]);

  return (
    <BrowserRouter>
      {!token ? (
        <Box
          sx={{
            height: "100vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            textAlign: "center",
            bgcolor: "background.default",
          }}
        >
          <Typography variant="h4" gutterBottom fontWeight="bold">
            Welcome to the Fitness Tracker App
          </Typography>
          <Typography variant="subtitle1" sx={{ mb: 4 }} color="text.secondary">
            Please login to access your activities and AI recommendations
          </Typography>
          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={() => logIn()}
          >
            LOGIN
          </Button>
        </Box>
      ) : (
        <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" elevation={1}>
            <Toolbar>
              <Typography
                variant="h6"
                component="div"
                sx={{ flexGrow: 1, fontWeight: "bold" }}
              >
                Fitness Tracker
              </Typography>
              <Button color="inherit" onClick={logOut}>
                Logout
              </Button>
            </Toolbar>
          </AppBar>
          <Box
            sx={{ p: 3, bgcolor: "#f5f5f5", minHeight: "calc(100vh - 64px)" }}
          >
            <Routes>
              <Route path="/activities" element={<ActvitiesPage />} />
              <Route path="/activities/:id" element={<ActivityDetail />} />
              <Route path="/" element={<Navigate to="/activities" replace />} />
            </Routes>
          </Box>
        </Box>
      )}
    </BrowserRouter>
  );
}

export default App;
