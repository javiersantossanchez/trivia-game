import { Serializable } from './Serializable';

export class Score implements Serializable{

  id: number;

  name: string;

  score: number;

  time: number;

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }


}
