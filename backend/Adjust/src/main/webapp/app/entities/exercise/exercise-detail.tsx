import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './exercise.reducer';
import { IExercise } from 'app/shared/model/exercise.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExerciseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExerciseDetail = (props: IExerciseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { exerciseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.exercise.detail.title">Exercise</Translate> [<b>{exerciseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="number">
              <Translate contentKey="adjustApp.exercise.number">Number</Translate>
            </span>
          </dt>
          <dd>{exerciseEntity.number}</dd>
          <dt>
            <span id="sets">
              <Translate contentKey="adjustApp.exercise.sets">Sets</Translate>
            </span>
          </dt>
          <dd>{exerciseEntity.sets}</dd>
          <dt>
            <span id="repsMin">
              <Translate contentKey="adjustApp.exercise.repsMin">Reps Min</Translate>
            </span>
          </dt>
          <dd>{exerciseEntity.repsMin}</dd>
          <dt>
            <span id="repsMax">
              <Translate contentKey="adjustApp.exercise.repsMax">Reps Max</Translate>
            </span>
          </dt>
          <dd>{exerciseEntity.repsMax}</dd>
          <dt>
            <Translate contentKey="adjustApp.exercise.move">Move</Translate>
          </dt>
          <dd>{exerciseEntity.moveId ? exerciseEntity.moveId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.exercise.workout">Workout</Translate>
          </dt>
          <dd>{exerciseEntity.workoutId ? exerciseEntity.workoutId : ''}</dd>
        </dl>
        <Button tag={Link} to="/exercise" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/exercise/${exerciseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ exercise }: IRootState) => ({
  exerciseEntity: exercise.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExerciseDetail);
