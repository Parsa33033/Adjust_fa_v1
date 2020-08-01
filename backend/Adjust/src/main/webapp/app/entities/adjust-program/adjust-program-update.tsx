import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { getEntities as getBodyCompositions } from 'app/entities/body-composition/body-composition.reducer';
import { IFitnessProgram } from 'app/shared/model/fitness-program.model';
import { getEntities as getFitnessPrograms } from 'app/entities/fitness-program/fitness-program.reducer';
import { INutritionProgram } from 'app/shared/model/nutrition-program.model';
import { getEntities as getNutritionPrograms } from 'app/entities/nutrition-program/nutrition-program.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { getEntities as getAdjustClients } from 'app/entities/adjust-client/adjust-client.reducer';
import { ISpecialist } from 'app/shared/model/specialist.model';
import { getEntities as getSpecialists } from 'app/entities/specialist/specialist.reducer';
import { getEntity, updateEntity, createEntity, reset } from './adjust-program.reducer';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustProgramUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustProgramUpdate = (props: IAdjustProgramUpdateProps) => {
  const [bodyCompostionId, setBodyCompostionId] = useState('0');
  const [fitnessProgramId, setFitnessProgramId] = useState('0');
  const [nutritionProgramId, setNutritionProgramId] = useState('0');
  const [clientId, setClientId] = useState('0');
  const [specialistId, setSpecialistId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const {
    adjustProgramEntity,
    bodyCompositions,
    fitnessPrograms,
    nutritionPrograms,
    adjustClients,
    specialists,
    loading,
    updating,
  } = props;

  const handleClose = () => {
    props.history.push('/adjust-program');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBodyCompositions();
    props.getFitnessPrograms();
    props.getNutritionPrograms();
    props.getAdjustClients();
    props.getSpecialists();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustProgramEntity,
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
          <h2 id="adjustApp.adjustProgram.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustProgram.home.createOrEditLabel">Create or edit a AdjustProgram</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustProgramEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-program-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-program-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="createdAtLabel" for="adjust-program-createdAt">
                  <Translate contentKey="adjustApp.adjustProgram.createdAt">Created At</Translate>
                </Label>
                <AvField id="adjust-program-createdAt" type="date" className="form-control" name="createdAt" />
              </AvGroup>
              <AvGroup>
                <Label id="expirationDateLabel" for="adjust-program-expirationDate">
                  <Translate contentKey="adjustApp.adjustProgram.expirationDate">Expiration Date</Translate>
                </Label>
                <AvField id="adjust-program-expirationDate" type="date" className="form-control" name="expirationDate" />
              </AvGroup>
              <AvGroup check>
                <Label id="designedLabel">
                  <AvInput id="adjust-program-designed" type="checkbox" className="form-check-input" name="designed" />
                  <Translate contentKey="adjustApp.adjustProgram.designed">Designed</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="doneLabel">
                  <AvInput id="adjust-program-done" type="checkbox" className="form-check-input" name="done" />
                  <Translate contentKey="adjustApp.adjustProgram.done">Done</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="paidLabel">
                  <AvInput id="adjust-program-paid" type="checkbox" className="form-check-input" name="paid" />
                  <Translate contentKey="adjustApp.adjustProgram.paid">Paid</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="adjust-program-bodyCompostion">
                  <Translate contentKey="adjustApp.adjustProgram.bodyCompostion">Body Compostion</Translate>
                </Label>
                <AvInput id="adjust-program-bodyCompostion" type="select" className="form-control" name="bodyCompostionId">
                  <option value="" key="0" />
                  {bodyCompositions
                    ? bodyCompositions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="adjust-program-fitnessProgram">
                  <Translate contentKey="adjustApp.adjustProgram.fitnessProgram">Fitness Program</Translate>
                </Label>
                <AvInput id="adjust-program-fitnessProgram" type="select" className="form-control" name="fitnessProgramId">
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
              <AvGroup>
                <Label for="adjust-program-nutritionProgram">
                  <Translate contentKey="adjustApp.adjustProgram.nutritionProgram">Nutrition Program</Translate>
                </Label>
                <AvInput id="adjust-program-nutritionProgram" type="select" className="form-control" name="nutritionProgramId">
                  <option value="" key="0" />
                  {nutritionPrograms
                    ? nutritionPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="adjust-program-client">
                  <Translate contentKey="adjustApp.adjustProgram.client">Client</Translate>
                </Label>
                <AvInput id="adjust-program-client" type="select" className="form-control" name="clientId">
                  <option value="" key="0" />
                  {adjustClients
                    ? adjustClients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="adjust-program-specialist">
                  <Translate contentKey="adjustApp.adjustProgram.specialist">Specialist</Translate>
                </Label>
                <AvInput id="adjust-program-specialist" type="select" className="form-control" name="specialistId">
                  <option value="" key="0" />
                  {specialists
                    ? specialists.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-program" replace color="info">
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
  bodyCompositions: storeState.bodyComposition.entities,
  fitnessPrograms: storeState.fitnessProgram.entities,
  nutritionPrograms: storeState.nutritionProgram.entities,
  adjustClients: storeState.adjustClient.entities,
  specialists: storeState.specialist.entities,
  adjustProgramEntity: storeState.adjustProgram.entity,
  loading: storeState.adjustProgram.loading,
  updating: storeState.adjustProgram.updating,
  updateSuccess: storeState.adjustProgram.updateSuccess,
});

const mapDispatchToProps = {
  getBodyCompositions,
  getFitnessPrograms,
  getNutritionPrograms,
  getAdjustClients,
  getSpecialists,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustProgramUpdate);
