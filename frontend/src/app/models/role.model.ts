export interface Role {
  id: number;
  name: string;
  createdAt: string;
  updatedAt: string;
}

export const roleRouteMap: { [key: string]: string } = {
  'SUPER_ADMIN': '/',
  'ADMIN': '/',
  'USER': '/',
  'ORGANISER': '/',
}
