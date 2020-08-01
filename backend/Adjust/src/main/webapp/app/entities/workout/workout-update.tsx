import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFitnessProgram } from 'app/shared/model/fitness-program.model';
import { getEntities as getFitnessPrograms } from 'app/entities/fitness-program/fitness-program.reducer';
import { getEntity, updateEntity, createEntity, reset } from './workout.reducer';
import { IWorkout } from 'app/shared/model/workout.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWorkoutUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const WorkoutUpdate = (props: IWorkoutUpdateProps) => {
  const [programId, setProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { workoutEntity, fitnessPrograms, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/workout');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getFitnessPrograms();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...workoutEntity,
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
          <h2 id="adjustApp.workout.home.createOrEditLabel">
            <Translate contentKey="adjustApp.workout.home.createOrEditLabel">Create or edit a Workout</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : workoutEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="workout-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="workout-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dayNumberLabel" for="workout-dayNumber">
                  <Translate contentKey="adjustApp.workout.dayNumber">Day Number</Translate>
                </Label>
                <AvField id="workout-dayNumber" type="string" className="form-control" name="dayNumber" />
              </AvGroup>
              <AvGroup>
                <Label for="workout-program">
                  <Translate contentKey="adjustApp.workout.program">Program</Translate>
                </Label>
                <AvInput id="workout-program" type="select" className="form-control" name="programId">
                  <option value="" key="0" />
                  {fitnessPrograms
                    ? fitnessPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/workout" replace color="info">
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
  fitnessPrograms: storeState.fitnessProgram.entities,
  workoutEntity: storeState.workout.entity,
  loading: storeState.workout.loading,
  updating: storeState.workout.updating,
  updateSuccess: storeState.workout.updateSuccess,
});

const mapDispatchToProps = {
  getFitnessPrograms,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(WorkoutUpdate);
