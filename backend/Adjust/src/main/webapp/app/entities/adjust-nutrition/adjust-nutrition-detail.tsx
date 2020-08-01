import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-nutrition.reducer';
import { IAdjustNutrition } from 'app/shared/model/adjust-nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustNutritionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustNutritionDetail = (props: IAdjustNutritionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustNutritionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustNutrition.detail.title">AdjustNutrition</Translate> [<b>{adjustNutritionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustNutrition.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.adjustNutrition.description">Description</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.description}</dd>
          <dt>
            <span id="unit">
              <Translate contentKey="adjustApp.adjustNutrition.unit">Unit</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.unit}</dd>
          <dt>
            <span id="adjustNutritionId">
              <Translate contentKey="adjustApp.adjustNutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.adjustNutritionId}</dd>
          <dt>
            <span id="caloriesPerUnit">
              <Translate contentKey="adjustApp.adjustNutrition.caloriesPerUnit">Calories Per Unit</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.caloriesPerUnit}</dd>
          <dt>
            <span id="proteinPerUnit">
              <Translate contentKey="adjustApp.adjustNutrition.proteinPerUnit">Protein Per Unit</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.proteinPerUnit}</dd>
          <dt>
            <span id="carbsPerUnit">
              <Translate contentKey="adjustApp.adjustNutrition.carbsPerUnit">Carbs Per Unit</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.carbsPerUnit}</dd>
          <dt>
            <span id="fatInUnit">
              <Translate contentKey="adjustApp.adjustNutrition.fatInUnit">Fat In Unit</Translate>
            </span>
          </dt>
          <dd>{adjustNutritionEntity.fatInUnit}</dd>
        </dl>
        <Button tag={Link} to="/adjust-nutrition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-nutrition/${adjustNutritionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustNutrition }: IRootState) => ({
  adjustNutritionEntity: adjustNutrition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustNutritionDetail);
