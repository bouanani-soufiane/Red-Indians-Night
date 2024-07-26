export interface Pageable <T> {
  content: T[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: {
    pageNumber: number,
    pageSize: number,
    offset: number,
    paged: boolean,
    sort: { sorted: boolean, unsorted: boolean, empty: boolean },
    unpaged: boolean
  },
  size: number;
  sort: { sorted: boolean, unsorted: boolean, empty: boolean };
  totalElements: number;
  totalPages: number;
}
