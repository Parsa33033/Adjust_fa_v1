import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMove } from 'app/shared/model/move.model';
import { getEntities as getMoves } from 'app/entities/move/move.reducer';
import { IWorkout } from 'app/shared/model/workout.model';
import { getEntities as getWorkouts } from 'app/entities/workout/workout.reducer';
import { getEntity, updateEntity, createEntity, reset } from './exercise.reducer';
import { IExercise } from 'app/shared/model/exercise.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExerciseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExerciseUpdate = (props: IExerciseUpdateProps) => {
  const [moveId, setMoveId] = useState('0');
  const [workoutId, setWorkoutId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { exerciseEntity, moves, workouts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/exercise');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMoves();
    props.getWorkouts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...exerciseEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.exercise.home.createOrEditLabel">
            <Translate contentKey="adjustApp.exercise.home.createOrEditLabel">Create or edit a Exercise</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : exerciseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="exercise-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="exercise-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="numberLabel" for="exercise-number">
                  <Translate contentKey="adjustApp.exercise.number">Number</Translate>
                </Label>
                <AvField id="exercise-number" type="string" className="form-control" name="number" />
              </AvGroup>
              <AvGroup>
                <Label id="setsLabel" for="exercise-sets">
                  <Translate contentKey="adjustApp.exercise.sets">Sets</Translate>
                </Label>
                <AvField id="exercise-sets" type="string" className="form-control" name="sets" />
              </AvGroup>
              <AvGroup>
                <Label id="repsMinLabel" for="exercise-repsMin">
                  <Translate contentKey="adjustApp.exercise.repsMin">Reps Min</Translate>
                </Label>
                <AvField id="exercise-repsMin" type="string" className="form-control" name="repsMin" />
              </AvGroup>
              <AvGroup>
                <Label id="repsMaxLabel" for="exercise-repsMax">
                  <Translate contentKey="adjustApp.exercise.repsMax">Reps Max</Translate>
                </Label>
                <AvField id="exercise-repsMax" type="string" className="form-control" name="repsMax" />
              </AvGroup>
              <AvGroup>
                <Label for="exercise-move">
                  <Translate contentKey="adjustApp.exercise.move">Move</Translate>
                </Label>
                <AvInput id="exercise-move" type="select" className="form-control" name="moveId">
                  <option value="" key="0" />
                  {moves
                    ? moves.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="exercise-workout">
                  <Translate contentKey="adjustApp.exercise.workout">Workout</Translate>
                </Label>
                <AvInput id="exercise-workout" type="select" className="form-control" name="workoutId">
                  <option value="" key="0" />
                  {workouts
                    ? workouts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/exercise" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  moves: storeState.move.entities,
  workouts: storeState.workout.entities,
  exerciseEntity: storeState.exercise.entity,
  loading: storeState.exercise.loading,
  updating: storeState.exercise.updating,
  updateSuccess: storeState.exercise.updateSuccess,
});

const mapDispatchToProps = {
  getMoves,
  getWorkouts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExerciseUpdate);
