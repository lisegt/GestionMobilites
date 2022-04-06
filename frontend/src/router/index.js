import { createRouter, createWebHistory } from "vue-router";
import Accueil from "@/views/admin/accueil/Accueil.vue";

const routes = [
  {
    path: "/admin",
    name: "Accueil",
    component: Accueil ,
  },

  { path: "/admin/etudiants",
    name: "Etudiants",
    component: Accueil,
  },

  { path: "/admin/destinations",
    name: "Destinations",
    component: Accueil,
  },

  { path: "/admin/mobilites",
    name: "Mobilites",
    component: Accueil,
  },

  { path: "/admin/documents",
    name: "Documents",
    component: Accueil,
  },

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
