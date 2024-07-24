export interface IQuestion {
  id: number;
  question: string;
  isRequired: boolean;
  isAutoGenerating: boolean;
  answerType: EAnswerType
}

export enum EAnswerType {
  BOOLEAN, TEXTN, NUMBER, DATE, MULTIPLE_CHOICE
}

