import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './adjust-nutrition.reducer';
import { IAdjustNutrition } from 'app/shared/model/adjust-nutrition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustNutritionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustNutritionUpdate = (props: IAdjustNutritionUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustNutritionEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/adjust-nutrition');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustNutritionEntity,
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
          <h2 id="adjustApp.adjustNutrition.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustNutrition.home.createOrEditLabel">Create or edit a AdjustNutrition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustNutritionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-nutrition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-nutrition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="adjust-nutrition-name">
                  <Translate contentKey="adjustApp.adjustNutrition.name">Name</Translate>
                </Label>
                <AvField id="adjust-nutrition-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="adjust-nutrition-description">
                  <Translate contentKey="adjustApp.adjustNutrition.description">Description</Translate>
                </Label>
                <AvField id="adjust-nutrition-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="unitLabel" for="adjust-nutrition-unit">
                  <Translate contentKey="adjustApp.adjustNutrition.unit">Unit</Translate>
                </Label>
                <AvField id="adjust-nutrition-unit" type="string" className="form-control" name="unit" />
              </AvGroup>
              <AvGroup>
                <Label id="adjustNutritionIdLabel" for="adjust-nutrition-adjustNutritionId">
                  <Translate contentKey="adjustApp.adjustNutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
                </Label>
                <AvField id="adjust-nutrition-adjustNutritionId" type="string" className="form-control" name="adjustNutritionId" />
              </AvGroup>
              <AvGroup>
                <Label id="caloriesPerUnitLabel" for="adjust-nutrition-caloriesPerUnit">
                  <Translate contentKey="adjustApp.adjustNutrition.caloriesPerUnit">Calories Per Unit</Translate>
                </Label>
                <AvField id="adjust-nutrition-caloriesPerUnit" type="string" className="form-control" name="caloriesPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="proteinPerUnitLabel" for="adjust-nutrition-proteinPerUnit">
                  <Translate contentKey="adjustApp.adjustNutrition.proteinPerUnit">Protein Per Unit</Translate>
                </Label>
                <AvField id="adjust-nutrition-proteinPerUnit" type="string" className="form-control" name="proteinPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="carbsPerUnitLabel" for="adjust-nutrition-carbsPerUnit">
                  <Translate contentKey="adjustApp.adjustNutrition.carbsPerUnit">Carbs Per Unit</Translate>
                </Label>
                <AvField id="adjust-nutrition-carbsPerUnit" type="string" className="form-control" name="carbsPerUnit" />
              </AvGroup>
              <AvGroup>
                <Label id="fatInUnitLabel" for="adjust-nutrition-fatInUnit">
                  <Translate contentKey="adjustApp.adjustNutrition.fatInUnit">Fat In Unit</Translate>
                </Label>
                <AvField id="adjust-nutrition-fatInUnit" type="string" className="form-control" name="fatInUnit" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-nutrition" replace color="info">
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
  adjustNutritionEntity: storeState.adjustNutrition.entity,
  loading: storeState.adjustNutrition.loading,
  updating: storeState.adjustNutrition.updating,
  updateSuccess: storeState.adjustNutrition.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustNutritionUpdate);
