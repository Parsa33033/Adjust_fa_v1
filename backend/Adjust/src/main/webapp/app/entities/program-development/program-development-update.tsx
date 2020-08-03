import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, reset } from './program-development.reducer';
import { IProgramDevelopment } from 'app/shared/model/program-development.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProgramDevelopmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProgramDevelopmentUpdate = (props: IProgramDevelopmentUpdateProps) => {
  const [adjustProgramId, setAdjustProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { programDevelopmentEntity, adjustPrograms, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/program-development');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustPrograms();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...programDevelopmentEntity,
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
          <h2 id="adjustApp.programDevelopment.home.createOrEditLabel">
            <Translate contentKey="adjustApp.programDevelopment.home.createOrEditLabel">Create or edit a ProgramDevelopment</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : programDevelopmentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="program-development-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="program-development-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="program-development-date">
                  <Translate contentKey="adjustApp.programDevelopment.date">Date</Translate>
                </Label>
                <AvField id="program-development-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="workoutScoreLabel" for="program-development-workoutScore">
                  <Translate contentKey="adjustApp.programDevelopment.workoutScore">Workout Score</Translate>
                </Label>
                <AvField id="program-development-workoutScore" type="string" className="form-control" name="workoutScore" />
              </AvGroup>
              <AvGroup>
                <Label id="fitnessScoreLabel" for="program-development-fitnessScore">
                  <Translate contentKey="adjustApp.programDevelopment.fitnessScore">Fitness Score</Translate>
                </Label>
                <AvField id="program-development-fitnessScore" type="string" className="form-control" name="fitnessScore" />
              </AvGroup>
              <AvGroup>
                <Label for="program-development-adjustProgram">
                  <Translate contentKey="adjustApp.programDevelopment.adjustProgram">Adjust Program</Translate>
                </Label>
                <AvInput id="program-development-adjustProgram" type="select" className="form-control" name="adjustProgramId">
                  <option value="" key="0" />
                  {adjustPrograms
                    ? adjustPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/program-development" replace color="info">
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
  adjustPrograms: storeState.adjustProgram.entities,
  programDevelopmentEntity: storeState.programDevelopment.entity,
  loading: storeState.programDevelopment.loading,
  updating: storeState.programDevelopment.updating,
  updateSuccess: storeState.programDevelopment.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustPrograms,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDevelopmentUpdate);
