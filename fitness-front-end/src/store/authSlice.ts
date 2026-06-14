import { createSlice } from "@reduxjs/toolkit";

interface UserState {
  user: string | null;
  token: string | null;
  userId: string | null;
}

const initialState = {
  user: JSON.parse(localStorage.getItem("user")!) || null,
  token: localStorage.getItem("token"),
  userId: localStorage.getItem("userId"),
} satisfies UserState as UserState;

const authSlice = createSlice({
  name: "counter",
  initialState,
  reducers: {
    setCredentials: (state, action) => {
        state.token=action.payload.token;
        state.user=action.payload.user;
        state.userId=action.payload.user.sub;

        localStorage.setItem("token",action.payload.token);
        localStorage.setItem("user",JSON.stringify(action.payload.user));
        localStorage.setItem("userId",action.payload.user.sub);
    },
    logout: (state) => {
        state.token=null;
        state.user=null;
        state.userId=null;

        localStorage.removeItem("token");
        localStorage.removeItem("user");
        localStorage.removeItem("userId");
    },
  },
});

export const { setCredentials, logout } = authSlice.actions;
export default authSlice.reducer;