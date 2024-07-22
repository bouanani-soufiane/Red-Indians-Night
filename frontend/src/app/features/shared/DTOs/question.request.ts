import { EAnswerType } from "../Models/question.model";

export interface QuestionRequest {
  question: string;
  isRequired: boolean;
  isAutoGenerating: boolean;
  answerType: EAnswerType
}
